package com.jinpaihushi.utils;

import java.util.List;

import com.jinpaihushi.jphs.system.model.SystemUser;

public class UrlUtils {

	public static final String DELIMITED_CHAR = ",";

	private UrlUtils() {
	}

	public static boolean isUrl(List<String> urlList, String url) {
		// System.out.println(privilegeList + "|ifAll|" + privilegeCode);
		if (StringUtils.isNotEmpty(url) && urlList != null) {
			String[] needPri = url.split(DELIMITED_CHAR);
			for (String pri : needPri) {
				if (!urlList.contains(pri)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	public static boolean isUrl(SystemUser user, String privilegeCode) {
		return isUrl(user.getPrivilegeList(), privilegeCode);
	}

	/*public static boolean isRight(List<String> rightList, String right) {
		// System.out.println(privilegeList + "|ifAll|" + privilegeCode);
		if (!StringUtils.isTrimEmpty(right) && rightList != null) {
			String[] needPri = right.split(DELIMITED_CHAR);
			for (String pri : needPri) {
				if (!rightList.contains(pri)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	public static boolean isRight(Users user, String privilegeCode) {
		return isRight(user.getPrivilegeList(), privilegeCode);
	}*/

}
