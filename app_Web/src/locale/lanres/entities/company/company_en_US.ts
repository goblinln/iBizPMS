import Company_en_US_Base from './company_en_US_base';

function getLocaleResource(){
    const Company_en_US_OwnData = {};
    const targetData = Object.assign(Company_en_US_Base(), Company_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;