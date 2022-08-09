package com.example.pagination;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Pagination {

	/** 現在のページ **/
	private int page;

	/** １ページあたりの表示件数 **/
	private int displaysPerPage;

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

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalDisplays() {
		return totalDisplays;
	}

	/**
	 * 総表示件数は、１ページあたりの表示件数と総ページ数から求める
	 * 
	 * @param totalDisplays
	 */
	public void setTotalDisplays(int displaysPerPage, int totalDisplays) {
		this.totalDisplays = totalDisplays / displaysPerPage;

		if (totalDisplays % displaysPerPage != 0) {
			totalDisplays++;
		}
	}

}
