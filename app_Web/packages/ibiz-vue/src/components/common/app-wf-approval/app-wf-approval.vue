<template>
    <div class='app-wf-approval'>
        <div class="app-wf-approval-header">
            <span class="approval-header-left">{{data.startTime}}</span>
            <span>{{data.startUserName}}{{$t('components.appwfapproval.commit')}}</span>
        </div>
        <div class="app-wf-approval-content" v-if="data.usertasks && data.usertasks.length >0">
            <template v-for="(usertask,index) in data.usertasks" >
                <template v-if="usertask.identitylinks.length >0">
                    <div class="approval-content-item" :key="index">
                        <div class="approval-content-item-left">
                            {{usertask.userTaskName}}
                        </div>
                        <div class="approval-content-item-right">
                            <div class="approval-content-item-wait" v-if="usertask.identitylinks.length >0">
                                {{$t('components.appwfapproval.wait')}}<span v-for="(identitylink,inx) in usertask.identitylinks" :key="inx">{{identitylink.displayname}}<span v-if="inx >0">、</span></span>{{$t('components.appwfapproval.handle')}}
                            </div>
                            <div class="approval-content-item-info" v-if="usertask.comments.length >0">
                                <div v-for="(comment,commentInx) in usertask.comments" :key="commentInx">
                                    <div class="approval-content-item-info-item approval-content-item-info-top">
                                        {{`【${comment.type}】${comment.fullMessage}`}}
                                    </div>
                                    <div class="approval-content-item-info-item approval-content-item-info-bottom">
                                        <span class="info-bottom-name">{{comment.authorName}}</span>
                                        <span>{{comment.time}}</span>
                                    </div>
                                </div>
                            </div>
                            <div class="approval-content-item-memo" v-if="usertask.userTaskId === viewparams.taskDefinitionKey">
                                <el-input type="textarea"  v-model="initmemo" :rows="2" @blur="handleBlur" :placeholder="$t('components.appwfapproval.placeholder')"></el-input>
                            </div>
                        </div>
                    </div>
                </template>
            </template>
            
        </div>
         <div class="app-wf-approval-bottom">
            <span v-if="data.endTime">{{data.endTime}}{{$t('components.appwfapproval.end')}}</span>
        </div>
    </div>
</template>
<script lang = 'ts'>
import { Vue, Component,Prop,Model } from 'vue-property-decorator';
import { GlobalService } from 'ibiz-service';

@Component({
})
export default class AppWFApproval extends Vue {

    /**
     * 双向绑定值
     * 
     *  @memberof AppWFApproval
     */
    @Model ('change')  value!: string;

    /**
     * 数据
     * 
     *  @memberof AppWFApproval
     */
    public data:any = {};

    /**
     *  初始化memo
     * 
     *  @memberof AppWFApproval
     */
    public initmemo:string = "";

    /**
     * 应用实体名称
     * 
     * @memberof ActionTimeline
     */
    @Prop() public appEntityCodeName!:string;

    /**
     *  上下文
     * 
     *  @memberof AppWFApproval
     */
    @Prop() public context:any;

    /**
     *  视图参数
     * 
     *  @memberof AppWFApproval
     */
    @Prop() public viewparams:any;

    /**
     * 初始化数据
     * 
     *  @memberof AppWFApproval
     */
    public created(){
        if(this.appEntityCodeName){
            new GlobalService().getService(this.appEntityCodeName).then((service: any) => {
                if(service){
                    service.GetWFHistory(this.context).then((res:any) =>{
                        if(res && (res.status === 200)){
                            this.data = res.data;
                        }
                    })
                }
            });
        }
    }

    /**
     * 抛出wfprocmemo
     * 
     *  @memberof AppWFApproval
     */
    public handleBlur($event:any){
         this.$emit('change',$event.target.value);
    }
   
}
</script>

<style lang="less">
@import './app-wf-approval.less';
</style>