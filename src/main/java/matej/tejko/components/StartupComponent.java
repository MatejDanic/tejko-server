package matej.tejko.components;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import matej.tejko.api.repositories.RoleRepository;
import matej.tejko.constants.TejkoConstants;
import matej.tejko.factories.AppFactory;
import matej.tejko.factories.RoleFactory;
import matej.tejko.api.repositories.AppRepository;
import matej.tejko.models.general.payload.requests.AppRequest;
import matej.tejko.models.general.payload.requests.RoleRequest;

@Component
public class StartupComponent implements ApplicationRunner {

        @Autowired
        RoleRepository roleRepository;

        @Autowired
        AppRepository appRepository;

        @Autowired
        RoleFactory roleFactory;

        @Autowired
        AppFactory appFactory;

        @Override
        public void run(ApplicationArguments args) throws Exception {

                if (roleRepository.findAll().size() == 0) {
                        roleRepository.saveAll(Arrays.asList(
                                        roleFactory.create(new RoleRequest(
                                                        TejkoConstants.ROLE_USER_ID,
                                                        TejkoConstants.ROLE_USER_LABEL,
                                                        TejkoConstants.ROLE_USER_DESCRIPTION)),
                                        roleFactory.create(new RoleRequest(
                                                        TejkoConstants.ROLE_ADMIN_ID,
                                                        TejkoConstants.ROLE_ADMIN_LABEL,
                                                        TejkoConstants.ROLE_ADMIN_DESCRIPTION)),
                                        roleFactory.create(new RoleRequest(
                                                        TejkoConstants.ROLE_MODERATOR_ID,
                                                        TejkoConstants.ROLE_MODERATOR_LABEL,
                                                        TejkoConstants.ROLE_MODERATOR_DESCRIPTION))));
                }

                if (appRepository.findAll().size() == 0) {
                        appRepository.saveAll(Arrays.asList(
                                        appFactory.create(new AppRequest(
                                                        TejkoConstants.APP_YAMB_ID,
                                                        TejkoConstants.APP_YAMB_NAME,
                                                        TejkoConstants.APP_YAMB_DESCRIPTION))));
                }

        }
}
