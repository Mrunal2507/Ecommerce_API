package com.cg.ecommerce.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cg.ecommerce.entity.CompanyProducts;
import com.cg.ecommerce.entity.UserProducts;
import com.cg.ecommerce.repository.AuthRepository;
import com.cg.ecommerce.repository.CompanyProductsRepository;
import com.cg.ecommerce.repository.UserProductsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserProductsService {

	@Autowired
    private UserProductsRepository userProductsRepository;
    
	@Autowired
    private AuthRepository userRepository;
    
	@Autowired
    private CompanyProductsRepository companyProductsRepository;
	
	@Autowired
    private ObjectMapper mapper;
    
	Logger logger = LoggerFactory.getLogger(UserProductsService.class);
    
	private final String FOLDER_PATH = "C://Users/MSAHEBRA/Downloads/E-CommercePortal-frontend11/E-CommercePortal-frontend/src/assets/File//";

    public boolean addUserProducts(String userProducts, String emailId,MultipartFile file) throws IOException {
        Random random = new Random();
        int randomNumber = random.nextInt(100000);
        String randomCode = String.format("%05d", randomNumber);
        UserProducts userProducts2 = new UserProducts();
        logger.info(userProducts.toString());
        if (userProducts != null) {
            String fileDestination = FOLDER_PATH + file.getOriginalFilename();
            Path currentfilename = Paths.get(fileDestination);
            String newFileName = "Img-" + randomCode + "." + getFileExtension(currentfilename);
            Path newFileNamePath = Paths.get(currentfilename.getParent().toString(), newFileName);
            try {
                file.transferTo(new File(newFileNamePath.toString()));
                userProducts2 = mapper.readValue(userProducts, UserProducts.class);
                userProducts2.setUserProductImage(newFileName);
                userProducts2.setAuth(userRepository.findById(emailId).get());
                userProducts2.setApprovalStatus("Pending");
                userProducts2.setRequestDate(new Date());
                if (userProductsRepository.save(userProducts2) != null)return true;

            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    static String getFileExtension(Path file) {
        String fileName = file.getFileName().toString();
        int dotIndex = fileName.lastIndexOf('.');
 
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1);
        } else {
            return "";
        }
    }

    public List<UserProducts> getAllUserProducts() {
        return userProductsRepository.findAll();
    }

    public List<UserProducts> getAllProductsByEmailId(String emailId) {
        return userProductsRepository.findByAuthEmailId(emailId);
    }

    public boolean changeApprovalStatusByUserProductId(int productId, String status) {
        Optional<UserProducts> newProduct = userProductsRepository.findById(productId);
        if (newProduct.isPresent()) {
            UserProducts userProductnew = newProduct.get();
            userProductnew.setApprovalStatus(status);
            userProductsRepository.save(userProductnew);
            if (status.equals("Accepted")) {
                CompanyProducts companyProducts = new CompanyProducts();
                companyProducts.setCategory(userProductnew.getCategory());
                companyProducts.setDescription(userProductnew.getDescription());
                companyProducts.setProductImagePath(userProductnew.getUserProductImage());
                companyProducts.setProductName(userProductnew.getUserProductName());
                companyProducts.setProductPrice(userProductnew.getUserProductPrice()+userProductnew.getUserProductPrice()*0.3);
                companyProductsRepository.save(companyProducts);
            }
            return true;
        }
        return false;
    }
    
    public List<UserProducts> getAllProductsByCategory(String category){
    	return userProductsRepository.findByCategory(category);
    }
    
    public List<UserProducts> getAllProductsByStatus(String approvalStatus){
    	return userProductsRepository.findAllByApprovalStatus(approvalStatus);
    }
}
