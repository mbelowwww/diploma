<template>
  <AppCenter>
    <div class="container-sessions">
      <AppInputGroup #group>
        <p class="titleMain">Расписание сеансов на: </p>
        <AppDatePicker v-model="startSession" :inputConf="dateStart" style="margin: 0 20px 0"/>
        <AppDatePicker v-model="endSession" :inputConf="dateEnd"/>
      </AppInputGroup>
      <AppTable :header="headers" :array="listSession" headerValue="val" width="1400" @clickItem="showHallItem"/>
      <AppPopupWindow v-if="isShowTable" @close="isShowTable = false">
        <AdminKitHall v-if="listPlaces.length > 0" :places="createdArray" :checkedPlaces="listPlaces" :reservationPlaces="reservationListPlaces" @clickPlace="addPlaceForReservation">
          <template #bottom>
            <p class="titleInfo"><span class="titleInfo">Название: </span>{{informationSessionById.session.film.name}}</p>
            <p class="titleInfo"><span class="titleInfo">Описание: </span>{{informationSessionById.session.film.description}}</p>
            <p class="titleInfo"><span class="titleInfo">Жанр: </span>{{informationSessionById.session.film.genre.name}}</p>
            <p class="titleInfo"><span class="titleInfo">Рейтинг: </span>{{informationSessionById.session.film.rating}}</p>
            <p class="titleInfo"><span class="titleInfo">Зал: </span>{{informationSessionById.session.hall.name}}</p>
            <p class="titleInfo"><span class="titleInfo">Длительность: </span>{{(informationSessionById.session.film.length / 3600).toFixed(2)}}<span> часов</span></p>
            <div class="wrapper-btn"><button class="btn-cabinet" @click="reservationPlaces">Забронировать</button></div>
          </template>
        </AdminKitHall>
      </AppPopupWindow>

    </div>
  </AppCenter>

</template>

<script>
import AppCenter from '../components/common/group/AppCenter'
import AppTable from '../components/common/table/AppTable'
import AppInputGroup from '../components/common/group/AppInputGroup'
import AppDatePicker from '../components/common/input/AppDatePicker'
import { dateToString } from '../_helper/project'
import AdminKitHall from '../components/admin/AdminKitHall'
import AppPopupWindow from '../components/popup/AppPopupWindow'

export default {
  name: 'PageSessions',
  components: { AppPopupWindow, AdminKitHall, AppDatePicker, AppInputGroup, AppTable, AppCenter },
  data () {
    return {
      listSession: [],
      startSession: new Date(2012, 0, 1, 0, 0, 0, 0),
      endSession: new Date(2022, 0, 1, 0, 0, 0, 0),
      dateStart: {
        type: 'datetime',
        disabled: false,
        placeholder: 'Начало сеансы'
      },
      dateEnd: {
        type: 'datetime',
        disabled: false,
        placeholder: 'Конец сеансы'
      },
      headers: [
        {
          id: 1,
          val: 'Начало сеанса',
          value: 'start'
        },
        {
          id: 2,
          val: 'Название фильма',
          value: 'film.name'
        },
        {
          id: 3,
          val: 'Продолжительность',
          value: 'film.length'
        },
        {
          id: 4,
          val: 'Жанр',
          value: 'film.genre.name'
        },
        {
          id: 5,
          val: 'Цена',
          value: 'price'
        }
      ],
      listPlaces: [],
      createdArray: [],
      isShowTable: false,
      dataReservation: {
        placesId: [],
        personName: '',
        sessionId: ''
      },
      selectedSession: {},
      reservationListPlaces: [],
      informationSessionById: {}
    }
  },
  watch: {
    endSession: {
      immediate: true,
      handler: function () {
        const correctData = {
          start: dateToString(this.startSession),
          end: dateToString(this.endSession)
        }
        this.$store.dispatch('sessions/getListSessions', correctData).then(response => this.listSession = response.data.map(item => ({
          ...item,
          price: '250'
        })))
      }
    }
  },
  methods: {
    showHallItem (item) {
      this.$store.dispatch('halls/getHallById', item.hall.id).then((response) => {
        this.toCreatePlaces(response.width, response.height)
        this.listPlaces = response.places
        this.isShowTable = true
        this.selectedSession = item
        this.$store.dispatch('reservation/getListPlacesById', this.selectedSession.id).then(responseReservation => this.reservationListPlaces = responseReservation)
        this.$store.dispatch('reservation/getListReservationsById', this.selectedSession.id).then(respData => this.informationSessionById = respData.find(item => item.session.id === this.selectedSession.id))
      })
    },
    toCreatePlaces (width, height) {
      this.createdArray = []
      if (height && width) {
        for (let i = 0; i < width; i++) {
          this.$set(this.createdArray, i, [])
          for (let j = 0; j < height; j++) {
            this.$set(this.createdArray[i], j, { x: i + 1, y: j + 1 })
          }
        }
      }
    },
    addPlaceForReservation (value) {
      const oldIndex = this.listPlaces.findIndex(item => item.x === value.x && item.y === value.y)
      const placeItem = this.listPlaces.find(item => item.x === value.x && item.y === value.y)
      if (oldIndex === -1) {
        return
      }
      const isExist = !!this.reservationListPlaces.find(val => val.id === placeItem.id)
      if (!isExist) { this.dataReservation.placesId.push(placeItem.id) }
      this.reservationListPlaces.push(placeItem)
    },
    reservationPlaces () {
      const { fName, name, lName } = this.$store.state.auth.currentUser
      this.dataReservation.personName = `${fName} ${name} ${lName}`
      this.dataReservation.sessionId = this.selectedSession.id
      this.$store.dispatch('reservation/createReservation', this.dataReservation).finally(() => {
        this.isShowTable = false
        this.dataReservation.placesId = []
      })
    }
  }
}
</script>

<style scoped lang="scss">
.container-sessions {
  background-color: rgba(26, 17, 28, 0.9);
  width: 100%;
  height: 800px;
  display: flex;
  padding: 20px;
  flex-direction: column;
}
</style>
