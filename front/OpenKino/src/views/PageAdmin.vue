<template>
  <div>
    <AdminHeader/>
    <AdminPanel
      :valuePanel="dataAdminPanel"
      :selected="selected"
      @click="selected = $event"
    />
    <AdminShow :showItemMenu="selected"/>
    <AppCenter :isVertical="true" :isAdmin="true">
      <AdminFilms v-if="selected.key === 1"/>
      <AdminSessions v-if="selected.key === 2"/>
      <AdminUsers v-if="selected.key === 3"/>
      <AdminHalls v-if="selected.key === 4"/>
    </AppCenter>
  </div>
</template>

<script>
import AdminHeader from '../components/admin/AdminHeader.vue'
import AdminPanel from '../components/admin/AdminPanel.vue'
import AdminShow from '../components/admin/AdminShow.vue'
import AppCenter from '../components/common/group/AppCenter'
import AdminFilms from '../components/admin/AdminFilms'
import AdminSessions from '../components/admin/AdminSessions'
import AdminUsers from '../components/admin/AdminUsers'
import AdminHalls from '../components/admin/AdminHalls'
export default {
  name: 'PageAdmin',
  components: {
    AdminHeader, AdminPanel, AdminShow, AppCenter, AdminFilms, AdminSessions, AdminUsers, AdminHalls
  },
  data () {
    return {
      dataAdminPanel: [
        {
          key: 1,
          val: 'Фильмы'
        },
        {
          key: 2,
          val: 'Сеансы'
        },
        {
          key: 3,
          val: 'Пользователи'
        },
        {
          key: 4,
          val: 'Залы'
        }
      ],
      selected: {}
    }
  }
}
</script>

<style scoped>

</style>
