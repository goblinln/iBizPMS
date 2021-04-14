import IbzMonthly_en_US_Base from './ibz-monthly_en_US_base';

function getLocaleResource(){
    const IbzMonthly_en_US_OwnData = {};
    const targetData = Object.assign(IbzMonthly_en_US_Base(), IbzMonthly_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;