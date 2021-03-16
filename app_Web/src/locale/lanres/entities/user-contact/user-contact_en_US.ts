import UserContact_en_US_Base from './user-contact_en_US_base';

function getLocaleResource(){
    const UserContact_en_US_OwnData = {};
    const targetData = Object.assign(UserContact_en_US_Base(), UserContact_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;