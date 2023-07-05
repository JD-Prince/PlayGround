//package com.project.playground.applicationmanager.database
//
//import com.project.playground.model.ParticipantsRegister
//import com.project.playground.model.SportActivity
//import com.project.playground.model.Player
//import com.project.playground.enums.Location
//import com.project.playground.enums.Sports
//
//object fakeDB {
//    val activityList = ArrayList<SportActivity>()
//    val userList = ArrayList<Player>()
//    val registeration = ArrayList<ParticipantsRegister>()
//    init {
//        activityList.apply {
//            add(
//                SportActivity(
//                    title = "AB RageField",
//                    Sports.THROWBALL,
//                    location =Location.GUINDY,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14
//                )
//            )
//            add(
//                SportActivity(
//                    title = "CD RageField A Long Trip >>>>>>>>>>>>>>>>>>",
//                    Sports.TENNIS,
//                    location =Location.VANDALOOR,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "EF RageField",
//                    Sports.FOOTBALL,
//                    location =Location.TAMBARAM,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "SR RageField",
//                    sports=Sports.BADMINTON,
//                    location =Location.CHROMPET,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "SR RageField",
//                    Sports.BASKETBALL,
//                    location =Location.CHENGALPATTU,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "SR RageField",
//                    Sports.CARROM,
//                    location =Location.AMBATUR,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14
//                )
//            )
//            add(
//                SportActivity(
//                    title = "AB RageField",
//                    Sports.THROWBALL,
//                    location =Location.GUINDY,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14
//                )
//            )
//            add(
//                SportActivity(
//                    title = "CD RageField A Long Trip >>>>>>>>>>>>>>>>>>",
//                    Sports.TENNIS,
//                    location =Location.VANDALOOR,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "EF RageField",
//                    Sports.FOOTBALL,
//                    location =Location.TAMBARAM,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "SR RageField",
//                    sports=Sports.BADMINTON,
//                    location =Location.CHROMPET,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "SR RageField",
//                    Sports.BASKETBALL,
//                    location =Location.CHENGALPATTU,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "SR RageField",
//                    Sports.CARROM,
//                    location =Location.AMBATUR,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14
//                )
//            )
//            add(
//                SportActivity(
//                    title = "AB RageField",
//                    Sports.THROWBALL,
//                    location =Location.GUINDY,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14
//                )
//            )
//            add(
//                SportActivity(
//                    title = "CD RageField A Long Trip >>>>>>>>>>>>>>>>>>",
//                    Sports.TENNIS,
//                    location =Location.VANDALOOR,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "EF RageField",
//                    Sports.FOOTBALL,
//                    location =Location.TAMBARAM,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "SR RageField",
//                    sports=Sports.BADMINTON,
//                    location =Location.CHROMPET,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "SR RageField",
//                    Sports.BASKETBALL,
//                    location =Location.CHENGALPATTU,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "SR RageField",
//                    Sports.CARROM,
//                    location =Location.AMBATUR,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14
//                )
//            )
//            add(
//                SportActivity(
//                    title = "AB RageField",
//                    Sports.THROWBALL,
//                    location =Location.GUINDY,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14
//                )
//            )
//            add(
//                SportActivity(
//                    title = "CD RageField A Long Trip >>>>>>>>>>>>>>>>>>",
//                    Sports.TENNIS,
//                    location =Location.VANDALOOR,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "EF RageField",
//                    Sports.FOOTBALL,
//                    location =Location.TAMBARAM,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "SR RageField",
//                    sports=Sports.BADMINTON,
//                    location =Location.CHROMPET,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "SR RageField",
//                    Sports.BASKETBALL,
//                    location =Location.CHENGALPATTU,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "SR RageField",
//                    Sports.CARROM,
//                    location =Location.AMBATUR,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14
//                )
//            )
//            add(
//                SportActivity(
//                    title = "AB RageField",
//                    Sports.THROWBALL,
//                    location =Location.GUINDY,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14
//                )
//            )
//            add(
//                SportActivity(
//                    title = "CD RageField A Long Trip >>>>>>>>>>>>>>>>>>",
//                    Sports.TENNIS,
//                    location =Location.VANDALOOR,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "EF RageField",
//                    Sports.FOOTBALL,
//                    location =Location.TAMBARAM,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "SR RageField",
//                    sports=Sports.BADMINTON,
//                    location =Location.CHROMPET,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "SR RageField",
//                    Sports.BASKETBALL,
//                    location =Location.CHENGALPATTU,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "SR RageField",
//                    Sports.CARROM,
//                    location =Location.AMBATUR,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14
//                )
//            )
//            add(
//                SportActivity(
//                    title = "AB RageField",
//                    Sports.THROWBALL,
//                    location =Location.GUINDY,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14
//                )
//            )
//            add(
//                SportActivity(
//                    title = "CD RageField A Long Trip >>>>>>>>>>>>>>>>>>",
//                    Sports.TENNIS,
//                    location =Location.VANDALOOR,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "EF RageField",
//                    Sports.FOOTBALL,
//                    location =Location.TAMBARAM,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "SR RageField",
//                    sports=Sports.BADMINTON,
//                    location =Location.CHROMPET,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "SR RageField",
//                    Sports.BASKETBALL,
//                    location =Location.CHENGALPATTU,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "SR RageField",
//                    Sports.CARROM,
//                    location =Location.AMBATUR,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14
//                )
//            )
//            add(
//                SportActivity(
//                    title = "AB RageField",
//                    Sports.THROWBALL,
//                    location =Location.GUINDY,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14
//                )
//            )
//            add(
//                SportActivity(
//                    title = "CD RageField A Long Trip >>>>>>>>>>>>>>>>>>",
//                    Sports.TENNIS,
//                    location =Location.VANDALOOR,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "EF RageField",
//                    Sports.FOOTBALL,
//                    location =Location.TAMBARAM,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "SR RageField",
//                    sports=Sports.BADMINTON,
//                    location =Location.CHROMPET,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "SR RageField",
//                    Sports.BASKETBALL,
//                    location =Location.CHENGALPATTU,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 2,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "SR RageField",
//                    Sports.CARROM,
//                    location =Location.AMBATUR,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 2,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14
//                )
//            )
//            add(
//                SportActivity(
//                    title = "AB RageField",
//                    Sports.THROWBALL,
//                    location =Location.GUINDY,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 2,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14
//                )
//            )
//            add(
//                SportActivity(
//                    title = "CD RageField A Long Trip >>>>>>>>>>>>>>>>>>",
//                    Sports.TENNIS,
//                    location =Location.VANDALOOR,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 2,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "EF RageField",
//                    Sports.FOOTBALL,
//                    location =Location.TAMBARAM,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 2,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "SR RageField",
//                    sports=Sports.BADMINTON,
//                    location =Location.CHROMPET,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 2,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "SR RageField",
//                    Sports.BASKETBALL,
//                    location =Location.CHENGALPATTU,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 1,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "SR RageField",
//                    Sports.CARROM,
//                    location =Location.AMBATUR,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14
//                )
//            )
//            add(
//                SportActivity(
//                    title = "AB RageField",
//                    Sports.THROWBALL,
//                    location =Location.GUINDY,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14
//                )
//            )
//            add(
//                SportActivity(
//                    title = "CD RageField A Long Trip >>>>>>>>>>>>>>>>>>",
//                    Sports.TENNIS,
//                    location =Location.VANDALOOR,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 3,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "EF RageField",
//                    Sports.FOOTBALL,
//                    location =Location.TAMBARAM,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 1,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "SR RageField",
//                    sports=Sports.BADMINTON,
//                    location =Location.CHROMPET,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 3,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "SR RageField",
//                    Sports.BASKETBALL,
//                    location =Location.CHENGALPATTU,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 3,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "SR RageField",
//                    Sports.CARROM,
//                    location =Location.AMBATUR,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 3,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14
//                )
//            )
//            add(
//                SportActivity(
//                    title = "AB RageField",
//                    Sports.THROWBALL,
//                    location =Location.GUINDY,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 3,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14
//                )
//            )
//            add(
//                SportActivity(
//                    title = "CD RageField A Long Trip >>>>>>>>>>>>>>>>>>",
//                    Sports.TENNIS,
//                    location =Location.VANDALOOR,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 3,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "EF RageField",
//                    Sports.FOOTBALL,
//                    location =Location.TAMBARAM,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 1,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "SR RageField",
//                    sports=Sports.BADMINTON,
//                    location =Location.CHROMPET,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 1,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "SR RageField",
//                    Sports.BASKETBALL,
//                    location =Location.CHENGALPATTU,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 1,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "SR RageField",
//                    Sports.CARROM,
//                    location =Location.AMBATUR,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 1,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14
//                )
//            )
//            add(
//                SportActivity(
//                    title = "AB RageField",
//                    Sports.THROWBALL,
//                    location =Location.GUINDY,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 1,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14
//                )
//            )
//            add(
//                SportActivity(
//                    title = "CD RageField A Long Trip >>>>>>>>>>>>>>>>>>",
//                    Sports.TENNIS,
//                    location =Location.VANDALOOR,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 1,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "EF RageField",
//                    Sports.FOOTBALL,
//                    location =Location.TAMBARAM,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 1,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "SR RageField",
//                    sports=Sports.BADMINTON,
//                    location =Location.CHROMPET,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 1,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "SR RageField",
//                    Sports.BASKETBALL,
//                    location =Location.CHENGALPATTU,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 1,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "SR RageField",
//                    Sports.CARROM,
//                    location =Location.AMBATUR,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 1,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14
//                )
//            )
//            add(
//                SportActivity(
//                    title = "AB RageField",
//                    Sports.THROWBALL,
//                    location =Location.GUINDY,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 1,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14
//                )
//            )
//            add(
//                SportActivity(
//                    title = "CD RageField A Long Trip >>>>>>>>>>>>>>>>>>",
//                    Sports.TENNIS,
//                    location =Location.VANDALOOR,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 2,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "EF RageField",
//                    Sports.FOOTBALL,
//                    location =Location.TAMBARAM,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 2,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "SR RageField",
//                    sports=Sports.BADMINTON,
//                    location =Location.CHROMPET,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 2,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "SR RageField",
//                    Sports.BASKETBALL,
//                    location =Location.CHENGALPATTU,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 2,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14,
//                )
//            )
//            add(
//                SportActivity(
//                    title = "SR RageField",
//                    Sports.CARROM,
//                    location =Location.AMBATUR,
//                    description = "This event has been sheduled by a SR corp for their CEO daughters Bday",
//                    host = 4,
//                    otherInstructions = "Players requested to bring their own equipments",
//                    requiredPlayers = 14
//                )
//            )
//        }
//
//        userList.apply {
//            add(Player(
//                userName = "JD007",
//                alias = "Jo",
//                password = "007",
//                bio = "Play your Goal",
//                phoneNumber = "9879876543"
//            ))
//            add(Player(
//                userName = "777",
//                alias = "Erion",
//                password = "007",
//                bio = "Random bio",
//                phoneNumber = "9879876541"
//            ))
//            add(Player(
//                userName = "Amal07",
//                alias = "Amalwin",
//                password = "007",
//                bio = "Edhuku ?",
//                phoneNumber = "9879876542"
//            ))
//            add(Player(
//                userName = "Bala07",
//                alias = "Bala",
//                password = "007",
//                bio = "Peace bro",
//                phoneNumber = "9879876540"
//            ))
//            add(Player(
//                userName = "DBKA07",
//                alias = "Leebika",
//                password = "007",
//                bio = "Arivu irukka",
//                phoneNumber = "9879876544"
//            ))
//            add(Player(
//                userName = "Din07",
//                alias = "Dinesh",
//                password = "007",
//                bio = "Thumbs up",
//                phoneNumber = "9879876545"
//            ))
//            add(Player(
//                userName = "Nits123",
//                alias = "Nitish",
//                password = "007",
//                bio = "Vazhka nadagama",
//                phoneNumber = "9879876545"
//            ))
//        }
//        registeration.apply {
//            add(ParticipantsRegister(3,4))
//            add(ParticipantsRegister(3,12))
//            add(ParticipantsRegister(3,11))
//            add(ParticipantsRegister(3,2))
//            add(ParticipantsRegister(4,11))
//            add(ParticipantsRegister(4,2))
//            add(ParticipantsRegister(4,8))
//            add(ParticipantsRegister(4,4))
//            add(ParticipantsRegister(4,9))
//            add(ParticipantsRegister(4,2))
//            add(ParticipantsRegister(2,8))
//            add(ParticipantsRegister(2,8))
//            add(ParticipantsRegister(2,9))
//            add(ParticipantsRegister(2,2))
//            add(ParticipantsRegister(2,50))
//            add(ParticipantsRegister(2,51))
//        }
//    }
//
//
//
//
//}