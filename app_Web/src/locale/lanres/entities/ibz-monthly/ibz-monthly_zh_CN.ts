import IbzMonthly_zh_CN_Base from './ibz-monthly_zh_CN_base';

function getLocaleResource(){
    const IbzMonthly_zh_CN_OwnData = {};
    const targetData = Object.assign(IbzMonthly_zh_CN_Base(), IbzMonthly_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;