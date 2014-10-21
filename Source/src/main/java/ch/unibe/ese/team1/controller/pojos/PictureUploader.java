package ch.unibe.ese.team1.controller.pojos;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * This class handles uploading any number of pictures of type
 * {@link MultipartFile} to the server.
 *
 */
public class PictureUploader {

	private static final int EXTENSION_LENGTH = 4;

	private String absoluteFilePath;
	private String relativePath;

	/**
	 * Creates a new PictureUploader that will upload to the directory specified
	 * by directory.
	 * 
	 * @param absolutePath
	 *            the file path of the directory that should be uploaded to
	 *            @param relativePath 
	 *            the file path the uploaded pictures will have in the servlet context
	 */
	public PictureUploader(String absolutePath, String relativePath ) {
		this.absoluteFilePath = absolutePath;
		this.relativePath = relativePath;
	}

	/**
	 * Uploads the given list of pictures to the saved directory. The pictures
	 * are named in ascending order with the filenames specified by the list of Strings returned.
	 * 
	 * @param pictures
	 *            the pictures to upload
	 * @return the filenames the pictures were uploaded as
	 */
	public List<String> upload(List<MultipartFile> pictures) {
		File directory = new File(absoluteFilePath);
		
		// create the directory if it does not exist yet
				if (!directory.exists()) {
					directory.mkdirs();
				}

		int firstIndex = findHighestIndexedPicture(directory);
		List<String> fileNames = new ArrayList<>();
		
		int currentIndex = firstIndex + 1;
		for (MultipartFile file : pictures) {
			if (!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();
					String originalFileName = file.getOriginalFilename();
					String extension = originalFileName
							.substring(originalFileName.length()
									- EXTENSION_LENGTH);
					String abosoluteFileName = absoluteFilePath + "/"
							+ currentIndex + extension;
					String relativeFileName = relativePath + "/" + currentIndex + extension;
					fileNames.add(relativeFileName);
					BufferedOutputStream outStream = new BufferedOutputStream(
							new FileOutputStream(new File(abosoluteFileName)));
					outStream.write(bytes);
					outStream.close();
					currentIndex++;
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}

		return fileNames;
	}

	/**
	 * Finds the picture with the highest index in the directory given by
	 * directory. This method assumes that the pictures are stored as with
	 * one-digit names in ascending order.
	 * 
	 * @param directory
	 *            the directory to search through
	 * @return the index of the highest-indexed picture
	 */
	private int findHighestIndexedPicture(File directory) {
		String[] files = directory.list();

		int max = 0;
		for (int i = 0; i < files.length; i++) {
			try {
				String indexString = files[i].substring(0, files[i].length() - EXTENSION_LENGTH);
				int index = Integer.parseInt(indexString);
				if (max < index) {
					max = index;
				}
			} catch (NumberFormatException ex) {
				// ignore, we don't care about these files anyway
			}
		}
		System.out.println("max: " + max);

		return max;
	}

}
