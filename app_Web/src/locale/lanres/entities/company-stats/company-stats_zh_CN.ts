import CompanyStats_zh_CN_Base from './company-stats_zh_CN_base';

function getLocaleResource(){
    const CompanyStats_zh_CN_OwnData = {};
    const targetData = Object.assign(CompanyStats_zh_CN_Base(), CompanyStats_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;