package com.payingguests.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.payingguests.exceptions.PayingGuestNotFoundException;
import com.payingguests.model.PayingGuest;
import com.payingguests.model.Category;
import com.payingguests.util.DbConnection;
import com.payingguests.util.PayingGuestMapper;
import com.payingguests.util.Queries;

public class PayingGuestDaoImpl implements IPayingGuestDao {

	/**
	 * This method parses the payingGuest object and adds it to the Paying Guest
	 * Table.
	 * 
	 * @author HariharanB
	 * @param payingGuest Paying Guest object to add the fields to the table
	 * @return Nothing
	 */
	@Override
	public void addPayingGuest(PayingGuest payingGuest) {
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			connection = DbConnection.openConnection();
			statement = connection.prepareStatement(Queries.INSERTPGQUERY);
			statement.setInt(1, payingGuest.getPayingGuestId());
			statement.setString(2, payingGuest.getPayingGuestName());
			statement.setString(3, payingGuest.getLocation());
			statement.setString(4, payingGuest.getCategory());
			System.out.println("Paying Guest added");
			statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbConnection.closeConnection();
		}

	}

	/**
	 * This method updates the category type of the referenced Paying Guest (with
	 * Paying Guest ID).
	 * 
	 * @author HariharanB
	 * @param roomId   The Paying Guest ID for updating
	 * @param category The category type that needs to be updated
	 * @return Integer - Update status
	 */
	@Override
	public int updatePayingGuest(int payingGuestId, String category) {
		PreparedStatement statement = null;
		Connection connection = null;
		int result = 0;
		try {
			connection = DbConnection.openConnection();
			statement = connection.prepareStatement(Queries.UPDATEPGCATEGORY);
			statement.setString(1, Category.valueOf(category).category);
			statement.setInt(2, payingGuestId);
			result = statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbConnection.closeConnection();
		}
		return result;
	}

	/**
	 * This method is responsible for retrieving all the Paying Guest records from
	 * the table
	 * 
	 * @author HariharanB
	 * @return The list containing all the payingGuest objects
	 */
	@Override
	public List<PayingGuest> findAllPayingGuest() throws PayingGuestNotFoundException {
		PreparedStatement statement = null;
		Connection connection = null;
		List<PayingGuest> payingGuests = new ArrayList<>();
		PayingGuestMapper mapper = null;
		try {
			ResultSet resultset = null;
			connection = DbConnection.openConnection();
			statement = connection.prepareStatement(Queries.SELECTPGQUERY, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			resultset = statement.executeQuery();
			mapper = new PayingGuestMapper();
			payingGuests = mapper.mapPayingGuestRow(resultset);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbConnection.closeConnection();
		}
		return payingGuests;
	}

	/**
	 * This method is responsible for returning a referenced Paying Guest (with
	 * Paying Guest ID)
	 * 
	 * @author HariharanB
	 * @param payingGuestId 
	 * @return payingGuest 
	 */
	@Override
	public PayingGuest findPayingGuestById(int payingGuestId) throws PayingGuestNotFoundException {
		PreparedStatement statement = null;
		Connection connection = null;
		PayingGuest payingGuest = null;
		try {
			ResultSet resultset = null;
			connection = DbConnection.openConnection();
			statement = connection.prepareStatement(Queries.QUERYBYPGID, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			statement.setInt(1, payingGuestId);
			resultset = statement.executeQuery();
			resultset.beforeFirst();
			while (resultset.next()) {
				payingGuest = new PayingGuest();
				payingGuest.setPayingGuestId(resultset.getInt(1));
				payingGuest.setPayingGuestName(resultset.getString(2));
				payingGuest.setLocation(resultset.getString(3));
				payingGuest.setCategory(resultset.getString(4));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbConnection.closeConnection();
		}
		return payingGuest;
	}

	/**
	 * This method is responsible for retrieving the Paying Guest records that match the
	 * given location.
	 * 
	 * @author HariharanB
	 * @param location To search for the Paying Guest with the matching location
	 * @return The list containing the  Paying Guest objects that match user given location
	 */
	@Override
	public List<PayingGuest> findPayingGuestByLocation(String location) throws PayingGuestNotFoundException {
		PreparedStatement statement = null;
		Connection connection = null;
		List<PayingGuest> payingGuests = new ArrayList<>();
		PayingGuestMapper mapper = null;
		try {
			ResultSet resultset = null;
			connection = DbConnection.openConnection();
			statement = connection.prepareStatement(Queries.QUERYBYLOCATION, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			statement.setString(1, location);
			resultset = statement.executeQuery();
			mapper = new PayingGuestMapper();
			payingGuests = mapper.mapPayingGuestRow(resultset);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbConnection.closeConnection();
		}
		return payingGuests;
	}

	/**
	 * This method is responsible for retrieving the Paying Guest records that match the
	 * given Paying Guest category.
	 * 
	 * @author HariharanB
	 * @param category To search for the rooms with this matching category
	 * @return The list containing the Paying Guest objects that match user given Paying Guest category
	 */
	@Override
	public List<PayingGuest> findPayingGuestByCategory(String category) throws PayingGuestNotFoundException {
		PreparedStatement statement = null;
		Connection connection = null;
		List<PayingGuest> payingGuests = new ArrayList<>();
		PayingGuestMapper mapper = null;
		try {
			ResultSet resultset = null;
			connection = DbConnection.openConnection();
			statement = connection.prepareStatement(Queries.QUERYBYCATEGORY, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			statement.setString(1, Category.valueOf(category).category);
			resultset = statement.executeQuery();
			mapper = new PayingGuestMapper();
			payingGuests = mapper.mapPayingGuestRow(resultset);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbConnection.closeConnection();
		}
		return payingGuests;
	}

	/**
	 * This method is responsible for deleting the referenced room (with Paying
	 * Guest ID).
	 * 
	 * @author HariharanB
	 * @param payingGuestId The Paying Guest ID for deleting
	 * @return Integer - Update status
	 */
	@Override
	public int deletePayingGuest(int payingGuestId) {
		PreparedStatement statement = null;
		Connection connection = null;
		int result = 0;
		try {
			connection = DbConnection.openConnection();
			statement = connection.prepareStatement(Queries.DELETEPGQUERY);
			statement.setInt(1, payingGuestId);
			result = statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbConnection.closeConnection();
		}
		return result;
	}

}
