<template>
  <Transition name="modal">
    <div v-if="show" class="modal-container">
      <div class="modal-wrap">
        <!--모달 헤더 -->
        <div class="modal-header">
          <slot name="header">default header</slot>
        </div>
        <!-- 모달 바디 -->
        <div class="modal-body">
          <slot name="body">default body</slot>
        </div>
      </div>
    </div>
  </Transition>
</template>

<script>
export default {
  props: {
    show: Boolean,
  },
};
</script>

<style scoped>
.modal-container {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  transition: opacity 0.3s ease;
}

.modal-wrap {
  margin: auto;
  background-color: #fff;
  border-radius: 4px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
  transition: all 0.3s ease;
}

.modal-header {
  padding: 50px 90px 24px;
  text-align: center;
  font-family: "AppleSDGothicNeoSB";
  font-size: 20px;
}

.modal-body {
  text-align: center;
  font-family: "AppleSDGothicNeoM";
  padding: 0 30px 30px;
}

/*
 * The following styles are auto-applied to elements with
 * transition="modal" when their visibility is toggled
 * by Vue.js.
 *
 * You can easily play with the modal transition by editing
 * these styles.
 */

.modal-enter-from {
  opacity: 0;
}

.modal-leave-to {
  opacity: 0;
}

.modal-enter-from .modal-container,
.modal-leave-to .modal-container {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}
</style>
