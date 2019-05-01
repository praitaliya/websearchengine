package Controller;

import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CreateTST {
	public static Trie trieObj;
	public static String destPath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\Computing-Project\\WEB-INF\\classes\\textfile";

	public static void createTrie() {
		if (trieObj == null) {
			System.out.println("Creating Trie for first time");
			try {
				trieObj = new Trie();
				File folderAF = new File(destPath);
				File[] listOfFilesAF = folderAF.listFiles();
				String x ="";
				String token;
				StringTokenizer strToken = null;

				for (File f : listOfFilesAF) {
					if (f.isFile()) {
						In in = new In(f);
						String textFiles = in.readAll();
						Scanner sc = new Scanner(textFiles);
						if (sc.hasNext()) {
							x = sc.nextLine().replaceAll("[^a-zA-Z0-9]+", " ");
						}
						strToken = new StringTokenizer(x, " ");

						while (strToken.hasMoreTokens()) {

							token = strToken.nextToken();
							trieObj.insert(token.toLowerCase());
						}
					}
					System.out.println("Trie Created");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Trie already available");
		}
	}
}
