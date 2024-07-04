package cj.cloudwave.member.service;

import cj.cloudwave.member.domain.Item;
import cj.cloudwave.member.domain.ItemImg;
import cj.cloudwave.member.dto.ItemDto;
import cj.cloudwave.member.dto.UpdateItemDto;
import cj.cloudwave.member.exception.NotEnoughStockException;
import cj.cloudwave.member.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Long addItem(ItemDto itemDto, String imageUrl) {
        ItemImg itemImg = new ItemImg(imageUrl);
        Item item = Item.createItem(itemDto.getName(), itemDto.getPrice(), itemDto.getStockQuantity(), itemDto.getCategory(), itemImg);
        itemRepository.save(item);
        return item.getId();
    }

    @Transactional
    public void updateItem(Long itemId, UpdateItemDto updateItemDto) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));

        // Update item details using domain methods
        item.updateItemDetails(updateItemDto.getName(), updateItemDto.getPrice(), updateItemDto.getStockQuantity());

        itemRepository.save(item);
    }

    @Transactional
    public void deleteItem(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));
        itemRepository.delete(item);
    }

    public ItemDto getItem(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));
        return convertToItemDto(item);
    }

    public List<ItemDto> searchItems(String keyword, int page, int size) {
        List<Item> items = itemRepository.findByNameContainingIgnoreCase(keyword);
        return items.stream()
                .map(this::convertToItemDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void addStock(Long itemId, int quantityToAdd) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));
        item.addStock(quantityToAdd);
    }

    @Transactional
    public void removeStock(Long itemId, int quantityToRemove) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));
        try {
            item.removeStock(quantityToRemove);
        } catch (NotEnoughStockException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private ItemDto convertToItemDto(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setName(item.getName());
        itemDto.setPrice(item.getPrice());
        itemDto.setStockQuantity(item.getStockQuantity());
        itemDto.setCategory(item.getCategory());
        itemDto.setImageUrl(item.getItemImg() != null ? item.getItemImg().getImage() : null);
        return itemDto;
    }
}
