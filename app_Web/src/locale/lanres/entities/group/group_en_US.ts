import Group_en_US_Base from './group_en_US_base';

function getLocaleResource(){
    const Group_en_US_OwnData = {};
    const targetData = Object.assign(Group_en_US_Base(), Group_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;