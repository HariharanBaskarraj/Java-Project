package com.payingguests.dao;

import java.util.List;

import com.payingguests.exceptions.PayingGuestNotFoundException;
import com.payingguests.model.PayingGuest;

public interface IPayingGuestDao {
	void addPayingGuest(PayingGuest payingGuest);

	int updatePayingGuest(int pgId, String category);

	List<PayingGuest> findAllPayingGuest() throws PayingGuestNotFoundException;

	PayingGuest findPayingGuestById(int pgId) throws PayingGuestNotFoundException;

	List<PayingGuest> findPayingGuestByLocation(String location) throws PayingGuestNotFoundException;

	List<PayingGuest> findPayingGuestByCategory(String category) throws PayingGuestNotFoundException;

	int deletePayingGuest(int pgId);

}
