import IbzDaily_en_US_Base from './ibz-daily_en_US_base';

function getLocaleResource(){
    const IbzDaily_en_US_OwnData = {};
    const targetData = Object.assign(IbzDaily_en_US_Base(), IbzDaily_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;