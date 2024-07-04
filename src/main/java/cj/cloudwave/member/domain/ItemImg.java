package cj.cloudwave.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemImg {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_img_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private String image;

    public ItemImg(String image) {
        this.image = image;
    }

    void setItem(Item item) {
        this.item = item;
    }
}
