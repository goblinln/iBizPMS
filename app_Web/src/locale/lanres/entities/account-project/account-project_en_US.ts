import AccountProject_en_US_Base from './account-project_en_US_base';

function getLocaleResource(){
    const AccountProject_en_US_OwnData = {};
    const targetData = Object.assign(AccountProject_en_US_Base(), AccountProject_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;