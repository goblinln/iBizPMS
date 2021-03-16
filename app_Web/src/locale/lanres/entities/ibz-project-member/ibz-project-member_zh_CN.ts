import IbzProjectMember_zh_CN_Base from './ibz-project-member_zh_CN_base';

function getLocaleResource(){
    const IbzProjectMember_zh_CN_OwnData = {};
    const targetData = Object.assign(IbzProjectMember_zh_CN_Base(), IbzProjectMember_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;