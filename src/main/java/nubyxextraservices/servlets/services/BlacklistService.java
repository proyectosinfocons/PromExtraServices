package nubyxextraservices.servlets.services;

import nubyxextraservices.servlets.dao.BlacklistDao;

public class BlacklistService {
    private final BlacklistDao blacklistDao;

    public BlacklistService(BlacklistDao blacklistDao) {
        this.blacklistDao = blacklistDao;
    }

    public boolean isPhoneNumberBlacklisted(String phoneNumber) {
        return blacklistDao.isPhoneNumberBlacklisted(phoneNumber);
    }
}
