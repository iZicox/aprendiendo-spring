package com.fundamentos.jpa.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class CheckListTask extends Task {

    @OneToMany(
            mappedBy = "checkListTask",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    @ToString.Exclude
    @Setter(AccessLevel.NONE)
    private List<CheckListItem> items = new ArrayList<>();


    public void addItem(CheckListItem item) {
        items.add(item);
        item.setCheckListTask(CheckListTask.this);
    }

    public void removeItem(CheckListItem item) {
        items.remove(item);
    }
}
