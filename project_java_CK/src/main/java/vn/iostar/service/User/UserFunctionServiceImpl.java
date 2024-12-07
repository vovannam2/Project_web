package vn.iostar.service.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import vn.iostar.entity.Parcel;
import vn.iostar.entity.User;
import vn.iostar.repository.ParcelRepository;
import vn.iostar.repository.UserRepository;

@Service
// handle user edit - delete- update!
public class UserFunctionServiceImpl {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ClientServiceImpl clientService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    ParcelRepository parcelRepository;

    public User checkEmailExist(String email){
        User user = userRepository.findByEmail(email);
        if(userRepository.existsByEmail(email)){
            return user;
        }else {
            return null;
        }
    }
    public void updatePassword(String email){
        User user = userRepository.findByEmail(email);
        String newPassword = clientService.create(user);
        user.setPassword(newPassword);
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
    public void print(){
        Parcel parcel = parcelRepository.findByParcelId(14);
        if(parcel != null){
            System.out.println(parcel.getParcelId() + " " + parcel.getParcelDetails());
        }else {
            System.out.println("khong co don hang");
        }
    }

}
