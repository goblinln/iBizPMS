import IbzAgent_en_US_Base from './ibz-agent_en_US_base';

function getLocaleResource(){
    const IbzAgent_en_US_OwnData = {};
    const targetData = Object.assign(IbzAgent_en_US_Base(), IbzAgent_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;