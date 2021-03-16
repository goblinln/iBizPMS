import Branch_en_US_Base from './branch_en_US_base';

function getLocaleResource(){
    const Branch_en_US_OwnData = {};
    const targetData = Object.assign(Branch_en_US_Base(), Branch_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;