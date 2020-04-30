/*
 * Copyright (c) 2014, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.provisioning.connector.scim;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.application.common.model.Property;
import org.wso2.carbon.identity.application.common.model.SubProperty;
import org.wso2.carbon.identity.provisioning.AbstractProvisioningConnectorFactory;
import org.wso2.carbon.identity.provisioning.IdentityProvisioningException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 */
public class SCIMProvisioningConnectorFactory extends AbstractProvisioningConnectorFactory {

    public static final String SCIM = "scim";
    private static final Log log = LogFactory.getLog(SCIMProvisioningConnectorFactory.class);

    @Override
    /**
     * @throws IdentityProvisioningException
     */
    protected SCIMProvisioningConnector buildConnector(Property[] provisioningProperties)
            throws IdentityProvisioningException {
        SCIMProvisioningConnector scimProvisioningConnector = new SCIMProvisioningConnector();
        scimProvisioningConnector.init(provisioningProperties);

        if (log.isDebugEnabled()) {
            log.debug("Created new connector of type : " + SCIM);
        }
        return scimProvisioningConnector;
    }

    @Override
    public String getConnectorType() {
        return SCIM;
    }

    /**
     * Get Configuration Properties.
     */
    @Override
    public List<Property> getConfigurationProperties() {

        List<Property> configProperties = new ArrayList<>();
        Property username = new Property();
        username.setName(SCIMProvisioningConnectorConstants.SCIM_USERNAME);
        username.setDisplayName("Username");
        username.setRequired(true);
        username.setType("string");
        username.setDisplayOrder(1);
        configProperties.add(username);

        Property password = new Property();
        password.setName(SCIMProvisioningConnectorConstants.SCIM_PASSWORD);
        password.setDisplayName("Password");
        password.setRequired(true);
        password.setType("string");
        password.setDisplayOrder(2);
        configProperties.add(password);

        Property userEp = new Property();
        userEp.setName(SCIMProvisioningConnectorConstants.SCIM_USER_EP);
        userEp.setDisplayName("User Endpoint");
        userEp.setRequired(true);
        userEp.setType("string");
        userEp.setDisplayOrder(3);
        configProperties.add(userEp);

        Property groupEp = new Property();
        groupEp.setName(SCIMProvisioningConnectorConstants.SCIM_GROUP_EP);
        groupEp.setDisplayName("Group Endpoint");
        groupEp.setRequired(false);
        groupEp.setType("string");
        groupEp.setDisplayOrder(4);
        configProperties.add(groupEp);

        Property userstoreDomain = new Property();
        userstoreDomain.setName(SCIMProvisioningConnectorConstants.SCIM_USERSTORE_DOMAIN);
        userstoreDomain.setDisplayName("User Store Domain");
        userstoreDomain.setRequired(false);
        userstoreDomain.setType("string");
        userstoreDomain.setDisplayOrder(5);
        configProperties.add(userstoreDomain);

        Property enablePwdProvisioning = new Property();
        enablePwdProvisioning.setName(SCIMProvisioningConnectorConstants.SCIM_ENABLE_PASSWORD_PROVISIONING);
        enablePwdProvisioning.setDisplayName("Enable Password Provisioning");
        enablePwdProvisioning.setRequired(false);
        enablePwdProvisioning.setDescription("Enable User password provisioning to a SCIM domain");
        enablePwdProvisioning.setType("boolean");
        enablePwdProvisioning.setDefaultValue("true");
        enablePwdProvisioning.setDisplayOrder(6);

        SubProperty defaultPwd = new SubProperty();
        defaultPwd.setName(SCIMProvisioningConnectorConstants.SCIM_DEFAULT_PASSWORD);
        defaultPwd.setDisplayName("Default Password");
        defaultPwd.setRequired(false);
        defaultPwd.setType("string");
        defaultPwd.setConfidential(true);
        enablePwdProvisioning.setSubProperties(new SubProperty[] {defaultPwd});
        configProperties.add(enablePwdProvisioning);

        return configProperties;
    }
}
