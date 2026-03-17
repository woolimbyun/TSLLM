package com.example.backend.service.user;

import com.example.backend.data.dao.UserDao;
import com.example.backend.data.dto.user.ApiKeyDto;
import com.example.backend.data.dto.user.JoinDto;
import com.example.backend.data.dto.user.JoinResponseDto;
import com.example.backend.data.entity.user.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class JoinService {

    private final UserDao userDao;
    private final TextEncryptor textEncryptor;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public JoinService(UserDao userDao, TextEncryptor textEncryptor, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.textEncryptor = textEncryptor;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public JoinResponseDto JoinProcess(JoinDto joinDto){
        String userId = joinDto.getUserId();
        String password = joinDto.getPassword();
        String accessKey = joinDto.getAccessKey();
        String secretKey = joinDto.getSecretKey();

        Boolean isExist = userDao.findByUserId(userId);

        if(isExist){
            return null;
        }
        User data = new User();

        data.setUserId(userId);
        data.setPassword(bCryptPasswordEncoder.encode(password));

        if(accessKey != null){
            data.setAccessKey(textEncryptor.encrypt(accessKey));
        }
        if(secretKey != null){
            data.setSecretKey(textEncryptor.encrypt(secretKey));
        }

        User insertUser =userDao.insertUser(data);
        JoinResponseDto joinResponseDto = new JoinResponseDto();
        joinResponseDto.setId(insertUser.getId());
        joinResponseDto.setUserId(insertUser.getUserId());

        return joinResponseDto;
    }

    public ApiKeyDto updateApiKey(ApiKeyDto apiKeyDto) throws Exception {
        Long id = apiKeyDto.getId();
        String accessKey = apiKeyDto.getAccessKey();
        String secretKey = apiKeyDto.getSecretKey();

        Boolean isExist = userDao.findById(id);

        if (isExist) {
            User user = userDao.updateApiKey(id, textEncryptor.encrypt(accessKey),textEncryptor.encrypt(secretKey));

            ApiKeyDto updateData= new ApiKeyDto();
            updateData.setId(user.getId());
            updateData.setAccessKey(user.getAccessKey());
            updateData.setSecretKey(user.getSecretKey());

            return updateData;
        }else {
            return null;
        }
    }
}
