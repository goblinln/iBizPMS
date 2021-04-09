import Branch_zh_CN_Base from './branch_zh_CN_base';

function getLocaleResource(){
    const Branch_zh_CN_OwnData = {};
    const targetData = Object.assign(Branch_zh_CN_Base(), Branch_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;