import React from 'react';
import CircularSlider from '@fseehawer/react-circular-slider';
import { BUDGET } from '../Session/Session';

export function Slider() {

    return (
        <CircularSlider
            label="KR"
            labelBottom={false}
            labelColor="#005a58"
            knobColor="#005a58"
            progressColorFrom="#00bfbd"
            progressColorTo="#F0A367"
            progressSize={24}
            trackColor="#eeeeee"
            trackSize={24}
            dataIndex={BUDGET.getBoundary()}
            knobPosition={"bottom"}
            valueFontSize={"3rem"}
            labelFontSize={"1.5rem"}
            min={0}
            max={25_000}
            verticalOffset={"2rem"}
            onChange={ value => { BUDGET.setBoundary(value); } }
        />
    );
}; 