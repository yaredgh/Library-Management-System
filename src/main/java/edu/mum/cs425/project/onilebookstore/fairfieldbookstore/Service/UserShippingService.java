package edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Service;

import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Modles.UserShipping;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface UserShippingService {
	Optional<UserShipping> findById(Long id);
	
	void removeById(Long id);
}
