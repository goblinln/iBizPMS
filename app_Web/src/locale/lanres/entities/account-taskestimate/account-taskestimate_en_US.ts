import AccountTaskestimate_en_US_Base from './account-taskestimate_en_US_base';

function getLocaleResource(){
    const AccountTaskestimate_en_US_OwnData = {};
    const targetData = Object.assign(AccountTaskestimate_en_US_Base(), AccountTaskestimate_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;