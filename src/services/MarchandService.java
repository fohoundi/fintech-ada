package services;

import dao.MerchantDao;
import model.Marchant;
import model.User;

import java.util.List;

public class MarchandService implements UserServiceInterface <Marchant> {

    private static final MerchantDao merchantDao = new MerchantDao();


    @Override
    public Marchant register(Marchant user) {
        return merchantDao.createMerchant(user);
    }

    @Override
    public Marchant update(Marchant user) {
        return merchantDao.updateMarchant(user);
    }

    @Override
    public Marchant findById(Long id) {
        return merchantDao.findMarchantById(id);
    }

    @Override
    public Marchant findByLogin(Long id) {
        return merchantDao.findMarchantByLogin(id);
    }

    @Override
    public List<Marchant> findAll() {
        return merchantDao.findAll();
    }

    @Override
    public void deleteById(Long id) {
        merchantDao.deleteMarchantById(id);
    }

    @Override
    public void deleteByLogin(Long id) {

    }
}
