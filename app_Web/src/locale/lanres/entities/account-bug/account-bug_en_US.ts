import AccountBug_en_US_Base from './account-bug_en_US_base';

function getLocaleResource(){
    const AccountBug_en_US_OwnData = {};
    const targetData = Object.assign(AccountBug_en_US_Base(), AccountBug_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;