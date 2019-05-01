package Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Controller {
	public static String path = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\Computing-Project\\WEB-INF\\classes\\textfile";
	public HashMap<String, Integer> countWords(String str) throws IOException {
		int totalWordCount = 0;
		HashMap<String, Integer> mapReturn = new HashMap<String, Integer>();
		String strWords = str;
		StringTokenizer strToknSerch = new StringTokenizer(strWords, " ");
		ArrayList<String> keyToknSerch = new ArrayList<String>();
		while (strToknSerch.hasMoreElements()) {
			keyToknSerch.add(strToknSerch.nextToken());
		}

		File fileArr[] = new File(path).listFiles();
		ArrayList<String> keyArrayList = new ArrayList<String>();

		for (File fileTemp : fileArr) {
			if (fileTemp.isFile()) {
				totalWordCount = 0;
				keyArrayList = new ArrayList<String>();

				String stringOfPage = new String(Files.readAllBytes(Paths.get(fileTemp.toURI())));
				StringTokenizer strTokn = new StringTokenizer(stringOfPage, " ");
				while (strTokn.hasMoreElements()) {
					keyArrayList.add(strTokn.nextToken());
				}

				for (int cntrPages = 0; cntrPages < keyArrayList.size(); cntrPages++) {
					for (int cntrWords = 0; cntrWords < keyToknSerch.size(); cntrWords++) {
						ArrayList<Integer> offset1a = search1(keyToknSerch.get(cntrWords).toCharArray(),
								keyArrayList.get(cntrPages).toCharArray());

						totalWordCount = totalWordCount + offset1a.size();
					}
				}
				mapReturn.put(fileTemp.getName().split("\\.")[0] + ".htm", totalWordCount);
			}
		}

		// System.out.println("Total no. of words : " +key.size());

		return mapReturn;
	}

	public static ArrayList<Integer> search1(char[] pattern, char[] text) {
		int M = pattern.length;
		int N = text.length;
		ArrayList<Integer> ans = new ArrayList<Integer>();
		for (int i = 0; i <= N - M; i++) {
			int j;
			for (j = 0; j < M; j++) {
				if (text[i + j] != pattern[j])
					break;
			}
			if (j == M) {
				ans.add(i);
				continue;// found at offset i
			}
		}
		return ans;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashMap sortedResult(HashMap hashData){
		List list = new LinkedList(hashData.entrySet());
		// Defined Custom Comparator here
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
			}
		});

		Collections.reverse(list);
		// Here I am copying the sorted list in HashMap
		// using LinkedHashMap to preserve the insertion order
		HashMap sortedHashMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedHashMap.put(entry.getKey(), entry.getValue());
		}
		System.out.println();
		return sortedHashMap;
	}
	@SuppressWarnings("unchecked")
	public String getSpellCheck(String word) {
		String result = "";
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		// String[] matchingword;
		String x = "";
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		for(File f : listOfFiles)
			if (f.isFile()) {

				In in = new In(f);
				String textFiles = in.readAll();

				Scanner sc = new Scanner(textFiles);
				if(sc.hasNextLine()) {
					x = sc.nextLine().replaceAll("[^a-zA-Z0-9]+", " ");
				}
				StringTokenizer strToken = new StringTokenizer(x, " ");

				String token;

				while (strToken.hasMoreTokens()) {
					token = strToken.nextToken();
					String a = token.replaceAll("\"", " ");

					if (a != " ") {
						int check = minDistance(word, token);
						hashMap.put(token, check);
					}
				}
			}

		HashMap<Integer, String> map = sortedResult(hashMap);
		Object[] keys = map.keySet().toArray();
		for(int i=1;i<=5;i++) {
			result += keys[keys.length-i].toString() + " , "; 
		}
		return result;
	}
	public int minDistance(String word1, String word2) {
		int len1 = word1.length();
		int len2 = word2.length();

		// len1+1, len2+1, because finally return dp[len1][len2]
		int[][] dp = new int[len1 + 1][len2 + 1];

		for (int i = 0; i <= len1; i++) {
			dp[i][0] = i;
		}

		for (int j = 0; j <= len2; j++) {
			dp[0][j] = j;
		}

		for (int i = 0; i < len1; i++) {
			char c1 = word1.charAt(i);
			for (int j = 0; j < len2; j++) {
				char c2 = word2.charAt(j);

				// if last two chars equal
				if (c1 == c2) {
					// update dp value for +1 length
					dp[i + 1][j + 1] = dp[i][j];
				} else {
					int replace = dp[i][j] + 1;
					int insert = dp[i][j + 1] + 1;
					int delete = dp[i + 1][j] + 1;

					int min = replace > insert ? insert : replace;
					min = delete > min ? min : delete;
					dp[i + 1][j + 1] = min;
				}
			}
		}
		return dp[len1][len2];
	}
}
