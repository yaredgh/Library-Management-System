package edu.mum.cs425.project.onilebookstore.fairfieldbookstore.ServiceImp;

import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Modles.UserShipping;
import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Repository.UserShippingRepository;
import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Service.UserShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserShippingServiceImpl implements UserShippingService {

    @Autowired
    private UserShippingRepository userShippingRepository;


    public Optional<UserShipping> findById(Long id) {
        return userShippingRepository.findById(id);
    }

    public void removeById(Long id) {
        userShippingRepository.deleteById(id);
    }

}
