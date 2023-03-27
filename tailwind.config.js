/** @type {import('tailwindcss').Config} */
module.exports = {
    mode: process.env.NODE_ENV ? 'jit' : undefined,
    content: ["./src/**/*.html", "./src/**/*.js"],
    theme: {
        colors: {
            transparent: 'transparent',
            'cpt11': '#EDF1D6',
            'cpt12': '#9DC08B',
            'cpt13': '#609966',
            'cpt14': '#40513B',
            'cpt21': '#3C6255',
            'cpt22': '#61876E',
            'cpt23': '#A6BB8D',
            'cpt24': '#EAE7B1',
            'white': '#FFFFFF',
            'black': '#0B0E0A',
            'error': '#C62828',
        },
        fontFamily: {
            'primary' : 'Montserrat, sans-serif',
            'secondary': 'Nunito, sans-serif',
        },
        fontWeight: {
            normal: '400',
            medium: '500',
            bold: '700',
            extra: '900',
        },
        extend: {
            gridTemplateColumns: {
                'product': 'repeat(auto-fill, minmax(70px, 1fr)',
            },
        },
    },
    variants: {
        extend: {},
    },
    plugins: [
        require('@tailwindcss/aspect-ratio'),
    ],
}