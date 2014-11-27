package ch.unibe.ese.team1.controller.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.unibe.ese.team1.model.User;
import ch.unibe.ese.team1.model.dao.UserDao;

/** Adds or removes ads from the user and updates the user accordingly */
@Service
public class BookmarkService {
	
	@Autowired
	User user;
	@Autowired
	UserDao userDao;
	
	/**
	 * This method adds or removes ads from the ArrayList.
	 * 
	 * @param id
	 *          it's the current ads' id
	 * @param bookmarked
	 *          tells the function the state of of the ad regarding bookmarks
	 * @param bookmarkedAds
	 *          users current list of bookmarked ads
	 * @param user
	 *          current user
	 *          
	 * @return returns an integer 3 bookmark it
	 *                            2 undo the bookmark
	 * 
	 */
	public int getBookmarkStatus(Long id, boolean bookmarked, ArrayList<Long> bookmarkedAds, User user) {
		int index = 0;
		if(bookmarked) {
			index = 0;
			for(long listedID : bookmarkedAds) {
				if(listedID == id) {
					bookmarkedAds.remove(index);
					index++;
				}
			}
			updateUser(bookmarkedAds, user);
			return 2;
		}
		
		if(!bookmarked) {
			bookmarkedAds.add(id);
			updateUser(bookmarkedAds, user);
			return 3;
		}
		
		return 1;
	}
	
	private void updateUser(ArrayList<Long> bookmarkedAds, User user) {
		user.setBookmarkedAds(bookmarkedAds);
		userDao.save(user);
	
	}
}
