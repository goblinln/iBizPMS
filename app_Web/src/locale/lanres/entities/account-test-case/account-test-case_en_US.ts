import AccountTestCase_en_US_Base from './account-test-case_en_US_base';

function getLocaleResource(){
    const AccountTestCase_en_US_OwnData = {};
    const targetData = Object.assign(AccountTestCase_en_US_Base(), AccountTestCase_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;