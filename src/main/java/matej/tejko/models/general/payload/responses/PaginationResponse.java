package matej.tejko.models.general.payload.responses;

import java.util.List;

public abstract class PaginationResponse<T> {

	private List<T> content;
	private int currentPage;
	private int totalPages;

	public List<T> getContent() {
		return content;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

}
