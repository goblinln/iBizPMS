<template>
  <transition name="el-message-fade" @after-leave="handleAfterLeave">
    <div
      :class="[
        'el-message',
        'app-notice',
        type && !iconClass ? `el-message--${ type }` : '',
        center ? 'is-center' : '',
        showClose ? 'is-closable' : '',
        customClass
      ]"
      :style="positionStyle"
      v-show="visible"
      @mouseenter="clearTimer"
      @mouseleave="startTimer"
      role="alert">
      <i :class="iconClass" v-if="iconClass"></i>
      <i :class="typeClass" v-else></i>
      <slot>
        <p v-if="!dangerouslyUseHTMLString" class="el-message__content">{{ message }}</p>
        <p v-else v-html="message" class="el-message__content"></p>
      </slot>
      <i v-if="showClose" class="el-message__closeBtn el-icon-close" @click="close"></i>
    </div>
  </transition>
</template>

<script type="text/babel">
  const typeMap = {
    success: 'success',
    info: 'info',
    warning: 'warning',
    error: 'error'
  };

  export default {
    data() {
      return {
        visible: false,
        message: '',
        duration: 3000,
        type: 'info',
        iconClass: '',
        customClass: '',
        onClose: null,
        showClose: false,
        closed: false,
        verticalOffset: 20,
        timer: null,
        dangerouslyUseHTMLString: false,
        center: false,
        position: 'top'
      };
    },

    computed: {
      typeClass() {
        return this.type && !this.iconClass
          ? `el-message__icon el-icon-${ typeMap[this.type] }`
          : '';
      },
      verticalProperty() {
        return /^top/.test(this.position) ? 'top' : 'bottom';
      },
      positionStyle() {
        return {
          [this.verticalProperty]: `${ this.verticalOffset }px`
        };
      },
    },

    watch: {
      closed(newVal) {
        if (newVal) {
          this.visible = false;
        }
      }
    },

    methods: {
      handleAfterLeave() {
        this.$destroy(true);
        this.$el.parentNode.removeChild(this.$el);
      },

      close() {
        this.closed = true;
        if (typeof this.onClose === 'function') {
          this.onClose(this);
        }
      },

      clearTimer() {
        clearTimeout(this.timer);
      },

      startTimer() {
        if (this.duration > 0) {
          this.timer = setTimeout(() => {
            if (!this.closed) {
              this.close();
            }
          }, this.duration);
        }
      },
      keydown(e) {
        if (e.keyCode === 27) { // esc关闭消息
          if (!this.closed) {
            this.close();
          }
        }
      }
    },
    mounted() {
      this.startTimer();
      document.addEventListener('keydown', this.keydown);
    },
    beforeDestroy() {
      document.removeEventListener('keydown', this.keydown);
    }
  };
</script>

<style lang="less">
// el-message样式修改
.el-message.app-notice{
    padding: 7px 8px;
    min-width: 100px;
    .el-message__icon{
      font-size: 28px;
    }
    .el-message__content{
      color: #fff;
      font-family: "Microsoft YaHei";
    }
}
// 配色修改
.app-notice.el-message--error{
  background-color: #f56c6c;
  border-color:#f56c6c;
  .el-icon-error{
    color: #fff;
    background-color: #f56c6c;
  }
}
.app-notice.el-message--warning{
  background-color: #e6a23c;
  border-color:#e6a23c;
  .el-icon-warning{
    color: #fff;
    background-color: #e6a23c;
  }
}
.app-notice.el-message--info{
  background-color: #909399;
  border-color:#909399;
  .el-icon-info{
    color: #fff;
    background-color: #909399;
  }
}
.app-notice.el-message--success{
  background-color: #24b47e;
  border-color:#24b47e;
  .el-icon-success{
    color: #fff;
    background-color: #24b47e;
  }
}
</style>
