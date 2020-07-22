package com.bezgrebelnygregory.testapp.core.mapper

import com.bezgrebelnygregory.testapp.core.model.BreedModel
import com.bezgrebelnygregory.testapp.core.resp.BreedResp

class BreedRespToBreedModelMapperImpl : BreedRespToBreedModelMapper {
    override fun map(from: BreedResp): BreedModel = BreedModel(
        affenpinscher = from.affenpinscher ?: listOf(),
        african = from.african ?: listOf(),
        airedale = from.airedale ?: listOf(),
        akita = from.akita ?: listOf(),
        appenzeller = from.appenzeller ?: listOf(),
        australian = from.australian ?: listOf(),
        basenji = from.basenji ?: listOf(),
        beagle = from.beagle ?: listOf(),
        bluetick = from.bluetick ?: listOf(),
        borzoi = from.borzoi ?: listOf(),
        bouvier = from.bouvier ?: listOf(),
        boxer = from.boxer ?: listOf(),
        brabancon = from.brabancon ?: listOf(),
        briard = from.briard ?: listOf(),
        buhund = from.buhund ?: listOf(),
        bulldog = from.bulldog ?: listOf(),
        bullterrier = from.bullterrier ?: listOf(),
        cairn = from.cairn ?: listOf(),
        cattledog = from.cattledog ?: listOf(),
        chihuahua = from.chihuahua ?: listOf(),
        chow = from.chow ?: listOf(),
        clumber = from.clumber ?: listOf(),
        cockapoo = from.cockapoo ?: listOf(),
        collie = from.collie ?: listOf(),
        coonhound = from.coonhound ?: listOf(),
        corgi = from.corgi ?: listOf(),
        cotondetulear = from.cotondetulear ?: listOf(),
        dachshund = from.dachshund ?: listOf(),
        dalmatian = from.dalmatian ?: listOf(),
        dane = from.dane ?: listOf(),
        deerhound = from.deerhound ?: listOf(),
        dhole = from.dhole ?: listOf(),
        dingo = from.dingo ?: listOf(),
        doberman = from.doberman ?: listOf(),
        elkhound = from.elkhound ?: listOf(),
        entlebucher = from.entlebucher ?: listOf(),
        eskimo = from.eskimo ?: listOf(),
        finnish = from.finnish ?: listOf(),
        frise = from.frise ?: listOf(),
        germanshepherd = from.germanshepherd ?: listOf(),
        greyhound = from.greyhound ?: listOf(),
        groenendael = from.groenendael ?: listOf(),
        havanese = from.havanese ?: listOf(),
        hound = from.hound ?: listOf(),
        husky = from.husky ?: listOf(),
        keeshond = from.keeshond ?: listOf(),
        kelpie = from.kelpie ?: listOf(),
        komondor = from.komondor ?: listOf(),
        kuvasz = from.kuvasz ?: listOf(),
        labrador = from.labrador ?: listOf(),
        leonberg = from.leonberg ?: listOf(),
        lhasa = from.lhasa ?: listOf(),
        malamute = from.malamute ?: listOf(),
        malinois = from.malinois ?: listOf(),
        maltese = from.maltese ?: listOf(),
        mastiff = from.mastiff ?: listOf(),
        mexicanhairless = from.mexicanhairless ?: listOf(),
        mix = from.mix ?: listOf(),
        mountain = from.mountain ?: listOf(),
        newfoundland = from.newfoundland ?: listOf(),
        otterhound = from.otterhound ?: listOf(),
        ovcharka = from.ovcharka ?: listOf(),
        papillon = from.papillon ?: listOf(),
        pekinese = from.pekinese ?: listOf(),
        pembroke = from.pembroke ?: listOf(),
        pinscher = from.pinscher ?: listOf(),
        pitbull = from.pitbull ?: listOf(),
        pointer = from.pointer ?: listOf(),
        pomeranian = from.pomeranian ?: listOf(),
        poodle = from.poodle ?: listOf(),
        pug = from.pug ?: listOf(),
        puggle = from.puggle ?: listOf(),
        pyrenees = from.pyrenees ?: listOf(),
        redbone = from.redbone ?: listOf(),
        retriever = from.retriever ?: listOf(),
        ridgeback = from.ridgeback ?: listOf(),
        rottweiler = from.rottweiler ?: listOf(),
        saluki = from.saluki ?: listOf(),
        samoyed = from.samoyed ?: listOf(),
        schipperke = from.schipperke ?: listOf(),
        schnauzer = from.schnauzer ?: listOf(),
        setter = from.setter ?: listOf(),
        sheepdog = from.sheepdog ?: listOf(),
        shiba = from.shiba ?: listOf(),
        shihtzu = from.shihtzu ?: listOf(),
        spaniel = from.spaniel ?: listOf(),
        springer = from.springer ?: listOf(),
        stbernard = from.stbernard ?: listOf(),
        terrier = from.terrier ?: listOf(),
        vizsla = from.vizsla ?: listOf(),
        waterdog = from.waterdog ?: listOf(),
        weimaraner = from.weimaraner ?: listOf(),
        whippet = from.whippet ?: listOf(),
        wolfhound = from.wolfhound ?: listOf()
    )
}