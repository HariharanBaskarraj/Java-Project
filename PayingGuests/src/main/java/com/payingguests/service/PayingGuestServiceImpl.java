package com.payingguests.service;

import java.util.List;
import java.util.stream.Collectors;

import com.payingguests.dao.IPayingGuestDao;
import com.payingguests.dao.PayingGuestDaoImpl;
import com.payingguests.exceptions.PayingGuestNotFoundException;
import com.payingguests.model.PayingGuest;

public class PayingGuestServiceImpl implements IPayingGuestService {

	IPayingGuestDao payingGuestDao = new PayingGuestDaoImpl();

	@Override
	public void addPayingGuest(PayingGuest payingGuest) {
		payingGuestDao.addPayingGuest(payingGuest);
	}

	@Override
	public int updatePayingGuest(int payingGuestId, String category) throws PayingGuestNotFoundException {
		int result = payingGuestDao.updatePayingGuest(payingGuestId, category);
		if (result == 0)
			throw new PayingGuestNotFoundException("The Paying Guest Category is not updated");
		return result;
	}

	@Override
	public List<PayingGuest> getAllPayingGuest() throws PayingGuestNotFoundException {
		List<PayingGuest> payingGuests = payingGuestDao.findAllPayingGuest();
		if (payingGuests.isEmpty())
			throw new PayingGuestNotFoundException("The Paying Guests are not available");
		return payingGuests.stream()
				.sorted((o1, o2) -> o1.getCategory().compareToIgnoreCase(o2.getCategory()))
				.collect(Collectors.toList());
	}

	@Override
	public PayingGuest getPayingGuestById(int payingGuestId) throws PayingGuestNotFoundException {
		PayingGuest payingGuest = payingGuestDao.findPayingGuestById(payingGuestId);
		if (payingGuest == null)
			throw new PayingGuestNotFoundException("The Paying Guest is not available");
		return payingGuest;
	}

	@Override
	public List<PayingGuest> getPayingGuestByLocation(String location) throws PayingGuestNotFoundException {
		List<PayingGuest> payingGuests = payingGuestDao.findPayingGuestByLocation(location);
		if (payingGuests.isEmpty())
			throw new PayingGuestNotFoundException("The Paying Guests in the given Location are not available");
		return payingGuests.stream()
				.sorted((o1, o2) -> o1.getCategory().compareToIgnoreCase(o2.getCategory()))
				.collect(Collectors.toList());	
	}

	@Override
	public List<PayingGuest> getPayingGuestByCategory(String category) throws PayingGuestNotFoundException {
		List<PayingGuest> payingGuests = payingGuestDao.findPayingGuestByCategory(category);
		if (payingGuests.isEmpty())
			throw new PayingGuestNotFoundException("The Paying Guests in the given Category are not available");
		return payingGuests.stream()
				.sorted((o1, o2) -> o1.getCategory().compareToIgnoreCase(o2.getCategory()))
				.collect(Collectors.toList());	
	}

	@Override
	public int deletePayingGuest(int payingGuestId) throws PayingGuestNotFoundException {
		int result = payingGuestDao.deletePayingGuest(payingGuestId);
		if (result == 0)
			throw new PayingGuestNotFoundException("The Paying Guest Category is deleted");
		return result;
	}

}
