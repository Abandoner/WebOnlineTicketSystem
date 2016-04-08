package page;

public class PageCt {
	private long l_start; // 开始纪录
	private long l_end; // 结束纪录
	private long l_curpage; // 当前页数
	private long l_totalnum;// 总记录数
	private int int_num = 5; // 每页10条
	private long l_totalpage; // 总的页数

	public void Init(long currentpage, long totalnum) {
		l_curpage = currentpage;
		l_totalnum = totalnum;

		if (currentpage >= 0) {
			if (currentpage >= (long) Math.ceil((double) l_totalnum
					/ (double) int_num))
				l_curpage = (long) Math.floor((double) l_totalnum
						/ (double) int_num);
			else
				l_curpage = currentpage;
		} else {
			l_curpage = 0;
		}
		l_start = l_curpage * int_num;
		l_end = l_start + int_num;
		if (l_end > l_totalnum)
			l_end = l_totalnum;
		l_totalpage = (long) Math.ceil((double) l_totalnum / (double) int_num);
	}

	public long getCurpage() {
		return l_curpage;
	}

	public long getPrepage() {
		if (l_curpage - 1 >= 0) {
			return l_curpage - 1;
		} else {
			return 0;
		}
	}

	public long getNextpage() {
		if (l_curpage + 1 <= l_totalpage) {
			return l_curpage + 1;
		} else {
			return l_totalpage;
		}
	}

	public long getTotalnum() {
		return l_totalnum;
	}

	public long getTotalpage() {
		return l_totalpage;
	}

	public long getStart() {
		return l_start;
	}

	public long getEnd() {
		return l_end;
	}

};
