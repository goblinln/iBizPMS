import CompanyStats_en_US_Base from './company-stats_en_US_base';

function getLocaleResource(){
    const CompanyStats_en_US_OwnData = {};
    const targetData = Object.assign(CompanyStats_en_US_Base(), CompanyStats_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;