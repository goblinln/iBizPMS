import User_en_US_Base from './user_en_US_base';

function getLocaleResource(){
    const User_en_US_OwnData = {};
    const targetData = Object.assign(User_en_US_Base(), User_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;