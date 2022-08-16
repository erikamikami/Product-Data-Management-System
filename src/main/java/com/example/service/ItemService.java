package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Item;
import com.example.form.ItemSearchForm;
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
	 */
	public List<Item> search(ItemSearchForm itemSearchForm, Pagination pagination){
		return itemRepository.search(itemSearchForm, pagination);
	}

}
