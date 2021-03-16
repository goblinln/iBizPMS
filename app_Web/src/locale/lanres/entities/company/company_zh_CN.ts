import Company_zh_CN_Base from './company_zh_CN_base';

function getLocaleResource(){
    const Company_zh_CN_OwnData = {};
    const targetData = Object.assign(Company_zh_CN_Base(), Company_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;