package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Item;
import com.example.entity.ItemSearch;
import com.example.pagination.Pagination;
import com.example.repository.ItemRepository;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	/**
	 * 全itemListを取得する
	 * @param pagination
	 * @return
	 */
	public List<Item> getAllItems(Pagination pagination){
		List<Item> itemList = itemRepository.findAll(pagination);
		return itemList;
	}
	
	/**
	 * ページング処理を行う
	 * @param pagination
	 * @return
	 */
	public Pagination paging(Pagination pagination){
		pagination.setTotalDisplays(itemRepository.countAllItems());
		pagination.setTotalPage(pagination.getDisplaysPerPage(), pagination.getTotalDisplays());
		return pagination;
	}
	
	/**
	 * idからitem詳細を取得する
	 * @param id
	 * @return
	 */
	public Item getDetail(int id) {
		Item item = itemRepository.findById(id);
		return item;
	}
	
	/**
	 * itemを登録する
	 * @param item
	 */
	@Transactional
	public void register(Item item) {
		int id = itemRepository.findMaxId();
		item.setId(++id);
		itemRepository.insert(item);
	}
	
	/**
	 * itemを編集する
	 * @param item
	 */
	public void edit(Item item) {
		itemRepository.update(item);
	}
	
	
	/**
	 * itemを検索する
	 * @param itemForm
	 * @return
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public List<Item> search(ItemSearch itemSearch, Pagination pagination) throws IllegalArgumentException, IllegalAccessException{
		System.out.println("検索条件は、" + itemSearch);
		if(itemSearch.isAllAtributesNull()) {
			System.out.println("全部nullです");
			return itemRepository.findAll(pagination);
		}
		System.out.println("全部nullではないです");
		return itemRepository.search(itemSearch, pagination);
	}

}
