package com.example.pagination;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Pagination {

	/** 現在のページ **/
	private int page = 1;

	/** １ページあたりの表示件数 **/
	private int displaysPerPage = 30;

	/** 総ページ数 **/
	private int totalPage;

	/** 総表示件数 **/
	private int totalDisplays;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getDisplaysPerPage() {
		return displaysPerPage;
	}

	public void setDisplaysPerPage(int displaysPerPage) {
		this.displaysPerPage = displaysPerPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * 総ページ数は、１ページあたりの表示件数と総表示件数から求める
	 * @param totalPage
	 */
	public void setTotalPage(int displaysPerPage, int totalDisplays) {
		this.totalPage = totalDisplays / displaysPerPage;
		
		if(totalDisplays % displaysPerPage != 0) {
			this.totalPage ++ ;
		}
	}

	public int getTotalDisplays() {
		return totalDisplays;
	}

	public void setTotalDisplays(int totalDisplays) {
		this.totalDisplays = totalDisplays;
	}

}
