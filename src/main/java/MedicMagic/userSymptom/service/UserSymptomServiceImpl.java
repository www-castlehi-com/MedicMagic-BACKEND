package MedicMagic.userSymptom.service;

import MedicMagic.userSymptom.dao.UserSymptomDao;
import MedicMagic.userSymptom.domain.UserSymptom;

import java.util.List;

public class UserSymptomServiceImpl implements UserSymptomService {
    UserSymptomDao userSymptomDao;

    public void setUserSymptomDao(UserSymptomDao userSymptomDao) {
        this.userSymptomDao = userSymptomDao;
    }

    @Override
    public void add(UserSymptom userSymptom) {
        userSymptomDao.add(userSymptom);
    }

    @Override
    public UserSymptom get(String id, String date) {
        return userSymptomDao.get(id, date);
    }

    @Override
    public List<UserSymptom> getAll() {
        return userSymptomDao.getAll();
    }

    @Override
    public List<UserSymptom> getEachId(String id) {
        return userSymptomDao.getEachId(id);
    }

    @Override
    public List<String> getSymptomTrue(UserSymptom userSymptom) {
        return userSymptomDao.getSymptomTrue(userSymptom);
    }

    @Override
    public void deleteAll() {
        userSymptomDao.deleteAll();
    }

    @Override
    public void update(UserSymptom userSymptom, String column, boolean object) {
        userSymptomDao.update(userSymptom, column, object);
    }
}
