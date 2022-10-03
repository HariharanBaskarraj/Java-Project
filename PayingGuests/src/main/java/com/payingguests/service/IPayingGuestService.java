package com.payingguests.service;

import java.util.List;

import com.payingguests.exceptions.PayingGuestNotFoundException;
import com.payingguests.model.PayingGuest;

public interface IPayingGuestService {

	void addPayingGuest(PayingGuest payingGuest);

	int updatePayingGuest(int pgId, String category);

	List<PayingGuest> getAllPayingGuest() throws PayingGuestNotFoundException;

	PayingGuest getPayingGuestById(int pgId) throws PayingGuestNotFoundException;

	List<PayingGuest> getPayingGuestByLocation(String location) throws PayingGuestNotFoundException;

	List<PayingGuest> getPayingGuestByCategory(String category) throws PayingGuestNotFoundException;

	int deletePayingGuest(int pgId);

	
}
