package com.sda.project.repository;


import com.sda.project.model.WishListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface WishListItemRepository extends JpaRepository<WishListItem,Long> {

}
